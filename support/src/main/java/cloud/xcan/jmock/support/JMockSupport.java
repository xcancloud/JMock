package cloud.xcan.jmock.support;

/**
 * JMock Support module — aggregates utility classes for mock data generation.
 * <p>
 * This module re-exports utilities from {@code jmock-api} and adds additional
 * support such as regex-based random generation (Generex).
 * <p>
 * Utility classes available from jmock-api:
 * <ul>
 *   <li>{@code cloud.xcan.jmock.api.support.utils.RandomUtils}</li>
 *   <li>{@code cloud.xcan.jmock.api.support.utils.RandomStringUtils}</li>
 *   <li>{@code cloud.xcan.jmock.api.support.utils.IPUtils}</li>
 *   <li>{@code cloud.xcan.jmock.api.support.utils.EncryptionUtils}</li>
 *   <li>{@code cloud.xcan.jmock.api.support.utils.UUIDUtils}</li>
 *   <li>{@code cloud.xcan.jmock.api.support.utils.SnowIdUtils}</li>
 *   <li>{@code cloud.xcan.jmock.api.support.utils.ColorUtil}</li>
 *   <li>{@code cloud.xcan.jmock.api.support.utils.EmojiGeneratorUtils}</li>
 *   <li>{@code cloud.xcan.jmock.api.support.utils.AppVersionUtils}</li>
 *   <li>{@code cloud.xcan.jmock.api.support.utils.RandomEmailUtils}</li>
 *   <li>{@code cloud.xcan.jmock.api.support.utils.StringToTypeUtils}</li>
 *   <li>{@code cloud.xcan.jmock.api.support.utils.TypeUtils}</li>
 *   <li>{@code cloud.xcan.jmock.api.support.revreg.RegRandom}</li>
 * </ul>
 *
 * @since 2.0.0
 */
public final class JMockSupport {

  private JMockSupport() {
  }
}
